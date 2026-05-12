package com.hito5.proyecto.view;

public enum FxmlView {

    LOGIN {
        @Override
        public String getTitle() {
            return "Login";
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Login.fxml";
        }
    },

    ADMIN {
        @Override
        public String getTitle() {
            return "Panel Administrador";
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/AdminView.fxml";
        }
    },

    ALUMNOS {
        @Override
        public String getTitle() {
            return "Gestión de Alumnos";
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/AlumnosView.fxml";
        }
    },

    ALUMNO_FORM {
        @Override
        public String getTitle() {
            return "Alumno";
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/AlumnoForm.fxml";
        }
    },
    EMPRESA_VIEW{
    	@Override
    	public String getTitle() {
    		return "Gestion de Empresas";
    	}
    	@Override
    	public String getFxmlFile() {
    		return "/fxml/EmpresasView.fxml";
    	}
    },
    EMPRESA_FORM{
    	@Override
    	public String getTitle() {
    		return "Datos de la empresa";
    	}
    	@Override
    	public String getFxmlFile() {
    		return "/fxml/EmpresaForm.fxml";
    	}
    	
    };

    public abstract String getTitle();
    public abstract String getFxmlFile();
}
	
