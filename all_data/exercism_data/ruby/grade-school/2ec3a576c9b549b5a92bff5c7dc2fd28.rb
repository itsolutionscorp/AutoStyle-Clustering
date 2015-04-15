class School

  def initialize()
    @school = Hash.new{ |h,k| h[k] = [] }
  end

  def db
    @school
  end

  def add(name, grade)
    db[grade] << name
  end

  def grade(grade)
    db[grade]
  end

  def sort()
    Hash[db.map { |grade, name| [ grade, name.sort ] }.sort]
  end
end
