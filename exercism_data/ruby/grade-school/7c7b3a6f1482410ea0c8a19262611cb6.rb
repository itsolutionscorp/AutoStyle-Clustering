class School

  attr_reader :db
  
  def initialize
    @db ||= Hash.new {|students, grade| students[grade] = []}
  end

  def add(student, grade)
    db[grade] << student
  end

  def grade year
    db[year] || []
  end

  def sort
    sorted = db.map { |grade,student| [grade, student.sort] }.sort
    Hash[sorted]
  end

end
