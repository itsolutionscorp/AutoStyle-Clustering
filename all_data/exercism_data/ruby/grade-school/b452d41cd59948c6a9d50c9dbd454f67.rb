class School

  def db
    @db ||= {}
  end

  def add(name, grade)
    db[grade] ||= []
    db[grade] << (name)
  end

  def grade(number)
    db[number] ||= []
  end

  def sort
    db.sort.each_with_object({}) do |(grade, names), sorted|
      sorted[grade] = names.sort
    end
  end
end
