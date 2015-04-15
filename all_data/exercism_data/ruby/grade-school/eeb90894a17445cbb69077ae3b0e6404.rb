class School
  def initialize name
  end

  def add name, grade
    db[grade] ||= []
    db[grade] << name
  end

  def grade grade
    db[grade] || []
  end

  def db
    @db ||= {}
  end

  def sort
    @db.keys.sort.each_with_object({}) do |grade, sorted|
      sorted[grade] = @db[grade].sort
    end
  end
end
