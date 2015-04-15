class School

  attr_reader :db

  def initialize
    @db = Hash.new {|h, k| h[k] = []}
  end

  def add(name, grade)
    @db[grade] << name
  end

  def grade(grade)
    @db[grade]
  end

  def sort
    sort_names sort_grades
  end

  def sort_names(grade_names)
    sorted ={}
    grade_names.each {|grade, names| sorted[grade] = names.sort}
    sorted
  end

  def sort_grades
    Hash[db.sort]
  end

end
