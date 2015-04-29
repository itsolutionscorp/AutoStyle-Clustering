class School

  def initialize(name)
    @school = Hash.new
  end

  def db
    @school
  end

  def add(student, grade)
    db[grade] ||= []
    db[grade] << student

  end

  def grade(grade_num)
    db[grade_num] ||= []
  end

  def sort
    sorted_names = Hash.new
    db.each {|k,v| sorted_names[k] = v.sort}
    sorted_names
  end

end
