class School
  attr_reader :db

  def initialize(name)
    @name = name
    @db = Hash.new{|db, grade| db[grade] = []}
  end

  def add(student, grade)
    @db[grade] << student
  end

  def grade(number)
    @db[number]
  end

  def sort
    Hash[@db.sort].each{|grade, students| students.sort!}
  end
end
