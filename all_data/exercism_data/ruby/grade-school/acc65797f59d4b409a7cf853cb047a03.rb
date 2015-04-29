class School

  def initialize
    @db = {}
  end

  def db
    @db.dup
  end

  def add(name, grade)
    @db[grade.to_i] ||= []
    @db[grade.to_i] << name.to_s unless @db[grade.to_i].include?(name.to_s)
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
