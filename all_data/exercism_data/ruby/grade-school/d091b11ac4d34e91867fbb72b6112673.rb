class School

  def initialize(name)
    @school = {}
  end

  def db
    @school
  end

  def add(student, grade_num)
    grade(grade_num) << student
  end

  def grade(number)
    db[number] ||= []
  end

  def sort
    sorted_names = {}
    db.each {|k,v| sorted_names[k] = v.sort}
    sorted_names
  end

end
