class School
  attr_reader :school, :db
  def initialize
    @db = {}
    @same_grade_student = []
  end

  def add(name, grade)
    if @db.keys.include?(grade)
      @db[grade] << name
    else
      @db[grade] = [name]
    end
  end

  def grade(grade)
    @db[grade] || ([])
  end

  def sort
    sorted = {}
    @db.keys.sort.each do |grade|
      sorted[grade] = (@db[grade].sort)
    end
    sorted
  end
end
