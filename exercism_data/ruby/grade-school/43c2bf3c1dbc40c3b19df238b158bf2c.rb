class School
  attr_reader :db

  def initialize
    @db = {}
  end

  def add(name, grade)
    name, grade = name.to_s, grade.to_i
    unless grade < 1 || name.empty?
      @db[grade.to_i] ||= []
      @db[grade.to_i] << name.to_s
    end
  end

  def grade(num)
    @db[num] || []
  end

  def sort
    Hash[
      @db.sort_by { |grade, name| grade }
         .map { |grade, names| [grade, names.sort] }
    ]
  end

end
