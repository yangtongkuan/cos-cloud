import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

function loadRoute(component) {
  return (resolve) => System.import('@/components/' + component).then((module) => {resolve(module)})
}

let routes = [
  {
    path: '/',
    name: 'login',
    component: loadRoute('login/Login')
  }
  ,{
    path: '/hellowWord',
    name: 'HellowWord',
    component: loadRoute('HelloWorld')
  }
]

export default new Router({routes})
