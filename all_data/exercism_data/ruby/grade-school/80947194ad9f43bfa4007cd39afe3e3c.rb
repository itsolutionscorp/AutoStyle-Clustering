class School
  attr_reader :db

  def initialize()
    @db = Hash.new do |hash, key|
      hash[key] = [] if key.is_a? Fixnum
    end
  end

  def add(student, grade)
    @db[grade] << student if student.is_a? String
  end

  def grade(grade)
    @db[grade]
  end

  def sort
    @db.values.each { |grade| grade.sort! }
    @db = Hash[@db.sort]
  end
end
