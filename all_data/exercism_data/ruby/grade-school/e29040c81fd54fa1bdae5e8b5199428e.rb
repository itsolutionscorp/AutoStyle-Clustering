class School

attr_accessor :db

  def initialize
    @db ||= Hash.new { |students, grade| students[grade] = [] }
  end

  def add(student, grade)
    db[grade] << student
  end

  def grade(section)
    db[section] || year
  end

  def sort
    Hash[collected]
  end

  def collected
    db.collect { |grade, students| [ grade, students.sort ] }.sort
  end

end
