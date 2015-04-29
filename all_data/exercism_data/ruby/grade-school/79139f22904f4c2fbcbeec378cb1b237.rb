class School

  def db
    @db ||= Hash.new
  end

  def add(name, grade)
    find_or_create(grade) << name
  end

  def grade(number)
    db[number] || []
  end

  def sort
    sorted_by_grade
  end

  private

  def sorted_by_grade
    db.keys.sort.each_with_object({}) do |grade, hash|
      hash[grade] = db[grade].sort
    end
  end

  def find_or_create(grade)
    db[grade] || db[grade] = []
  end

end
