# school
class School
  def initialize
    @students = {}
  end

  def add(name, grade)
    if @students[grade].nil?
      @students[grade] = [name]
    else
      @students[grade] << name
    end
  end

  def grade(grade)
    if @students[grade]
      @students[grade].sort 
    else
      []
    end
  end

  def sort
    sorted = @students
    @sorted.each {|grade| grade.sort! }
    @sorted.sort.to_h
  end

  def to_hash
    sort
  end
end
