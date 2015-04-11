#school.py
#Back to school, back to school to prove to dad that I'm no fool!
#
#Modified the assignment to become an sqlite3 demo
#check out the tests I used at https://github.com/gregorypease280/exercism/
import sqlite3


class School:

    def __init__(self, name="The School with No Name", school_db='school_db', school_tbl='SCHOOL_TBL'):
        self.name = name
        self.school_db = school_db
        self.school_tbl = school_tbl
        self.connect_to_db()

    def connect_to_db(self):
        try:
            db = sqlite3.connect(self.school_db)
            cursor = db.cursor()
            cursor.execute('create table if not exists ' + self.school_tbl +
                           '(id INTEGER PRIMARY KEY, name TEXT, grade INTEGER)')
            db.commit()

        except Exception as e:
            db.rollback()
            raise e

        finally:
            db.close()

    def add(self, name, grade):
        try:
            db = sqlite3.connect(self.school_db)
            cursor = db.cursor()
            cursor.execute('INSERT INTO ' + self.school_tbl + '(name, grade) VALUES(:name, :grade)', {'name': name, 'grade': grade})
            db.commit()

        except Exception as e:
            db.rollback()
            raise e

        finally:
            db.close()

    def delete_all(self):
        try:
            db = sqlite3.connect(self.school_db)
            cursor = db.cursor()
            cursor.execute('DELETE FROM ' +self.school_tbl)
            db.commit()
            self.connect_to_db()

        except Exception as e:
            db.rollback()
            raise e

        finally:
            db.close()

    def db(self):
        try:
            db = sqlite3.connect(self.school_db)
            cursor = db.cursor()
            cursor.execute('SELECT grade, name FROM ' + self.school_tbl)
            rows = cursor.fetchall()
            mydict = {}
            for row in rows:
                if row[0] in mydict.keys():
                    mydict[row[0]].add(str(row[1]))

                else:
                    mydict[row[0]] = set()
                    mydict[row[0]].add(str(row[1]))
            return mydict

        except Exception as e:
            db.rollback()
            raise e

        finally:
            db.close()

    def grade(self, selected_grade):
        try:
            db = sqlite3.connect(self.school_db)
            cursor = db.cursor()
            cursor.execute('SELECT grade, name FROM ' + self.school_tbl + ' WHERE grade=?', (selected_grade,))
            rows = cursor.fetchall()
            mydict = {}
            if rows:
                for row in rows:
                    if row[0] in mydict.keys():
                        mydict[row[0]].add(str(row[1]))

                    else:
                        mydict[row[0]] = set()
                        mydict[row[0]].add(str(row[1]))
                print mydict
                return mydict[row[0]]
            else:
                return set()

        except Exception as e:
            db.rollback()
            raise e

        finally:
            db.close()

    def sort(self):
        tmp = self.db()
        for row in tmp.keys():
            tmp[row] = sorted(list(tmp[row]))
        return tmp
