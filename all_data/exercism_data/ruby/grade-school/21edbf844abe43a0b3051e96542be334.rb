class School

  def initialize
    @db = {}
  end

  def db
    @db
  end

  def add(name, grade)
    @db[grade] ||= []
    @db[grade] << name
  end

  def grade(grade)
    @db[grade] || []
  end

  def sort
    @db.sort_by(&:first).each_with_object({}) do |obj, hsh|
      hsh[obj.first] = obj.last.sort
    end
  end

end
