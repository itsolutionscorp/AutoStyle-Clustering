class School
  def initialize
    @school = {}
  end

  def add(name, grade)
    create_grade(grade) unless(@school.has_key? grade)
    add_name(name, grade)
  end

  def create_grade(grade)
    @school[grade] = []
  end

  def add_name(name, grade)
    @school[grade].push(name)
  end

  def grade number
   @school.has_key?(number) ? @school[number].sort : []
  end
  
  def to_hash
    @school.sort.each{|key, value| value.sort!}.to_h
  end
end
