class School
  attr_reader :db

  def initialize(name)
    @name = name
    @db = {}
  end

  def add(student, grade)
    @db[grade] ||= []
    @db[grade] << student
  end

  def grade(grade)
    @db[grade]
  end

  def sort
    Hash[*@db.map { |k,v| [k, v.sort] }.sort.flatten(1)]
  end
end
