class School
  attr_accessor :db

  def initialize(name)
    @name = name
    @db = {}
  end

  def add(student, grade)
    @db[grade] = [] if @db[grade].nil?
    @db[grade].push(student)
  end

  def grade(grade)
    @db[grade] = [] if @db[grade].nil?
    @db[grade]
  end

  def sort
    Hash[@db.sort.each {|k, v| v.sort!}]
  end
end
