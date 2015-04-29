class School
  def initialize
    @students = Array.new(13) { Array.new }
  end

  def add(name, grade)
    @students[grade] << name
  end

  def db
    output = {}

    @students.each_with_index do |students, grade|
      output[grade] = students if students.size > 0 
    end

    output
  end

  def sort
    db.each { |grade, students| students.sort! }
  end

  def grade(grade)
    @students[grade]
  end

end
