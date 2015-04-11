class School
	attr_accessor :db

  def initialize
    @db = {}
  end

  def add(name, grade)
    db[grade] ||= []
    db[grade] << name
  end

  def grade(grade)
    db[grade] || []   
  end

  def sort
    sorted_school = {}
    db.keys.sort.each do |grade|
      sorted_school[grade] = db[grade].sort
    end
    sorted_school
  end

end
