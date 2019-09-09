var storage = {
    set(key, value) { //保存数据
        localStorage.setItem(key, JSON.stringify(value));
    },
    get(key) { //获得数据
        return JSON.parse(localStorage.getItem(key));
    },
    remove(key) { //移除数据
        localStorage.removeItem(key);
    }
};

export default storage;
