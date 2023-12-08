CREATE DATABASE organization_management;

USE organization_management;

CREATE TABLE organization (
    id int AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    description LONGTEXT,
    address varchar(255),
    email varchar(30),
    phone varchar(15),
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY (name)
);

CREATE TABLE sub_organization (
    id INT AUTO_INCREMENT,
    organization_id INT,
    name varchar(255) NOT NULL,
    description LONGTEXT,
    address varchar(255),
    email varchar(30),
    phone varchar(15),
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    UNIQUE KEY (name),
    CONSTRAINT `FK_sub_org__org_id` FOREIGN KEY (organization_id) REFERENCES organization(id)
);

CREATE TABLE role (
    id INT AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description TEXT,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY (name)
);

CREATE TABLE Department (
    id INT AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description TEXT,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    UNIQUE KEY (name)
);

CREATE TABLE employee (
    id INT AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100),
    phone_number VARCHAR(15),
    age int,
    address VARCHAR(255),
    gender CHAR(1),
    department_id INT,
    role_id INT,
    sub_organization_id INT,
    hire_date DATE,
    salary DECIMAL(10, 2),
    active_status boolean DEFAULT true,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY (email),
    CONSTRAINT `FK_employee__dept_id` FOREIGN KEY (department_id) REFERENCES department(id),
    CONSTRAINT `FK_employee__role_id` FOREIGN KEY (role_id) REFERENCES role(id),
    CONSTRAINT `FK_employee__sub_org_id` FOREIGN KEY (sub_organization_id) REFERENCES sub_organization(id)
);