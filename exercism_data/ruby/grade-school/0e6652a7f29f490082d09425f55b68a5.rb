class School
  def initialize
    @roster = {}
  end

  def add(name, grade)
    student = Student.new(name, grade)
    if @roster[grade].nil?
      @roster[grade] = []
    end
    @roster[grade].push name
  end

  def to_hash
    r =  @roster.sort
    Hash[r.map {|key, value| [key, value.sort]}]
  end

  def grade(grade)
    if @roster[grade].nil?
      return []
    end
    @roster[grade].sort
  end
end

class Student
  def initialize(n, g)
    @name = n
    @grade = g
  end
end
