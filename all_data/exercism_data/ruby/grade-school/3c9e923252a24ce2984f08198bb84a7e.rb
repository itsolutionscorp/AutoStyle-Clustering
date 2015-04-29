class School
attr_accessor :db

def initialize
  @db = Hash.new{[]}
end

def add(student_name, grade)
  @db[grade] <<= student_name   # "@db[grade] =" is obviated by the "=" after the "<<"
end

def grade(grade_num)
  @db[grade_num]
end

def sort
  @db.each { |key, value| @db[key] = value.sort }
  @db = Hash[@db.sort]          # sort makes an array, pass it to Hash to rehashify
end

end
