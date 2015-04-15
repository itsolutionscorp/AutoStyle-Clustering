class School

  attr_accessor :db

  def initialize
    self.db = {}
  end

  def add(name, grade_number)
    self.db[grade_number] ||= []
    self.db[grade_number] << name
  end

  def grade(grade_number)
    self.db[grade_number] ||= []
  end

  def sort
    self.db.keys.sort.each.with_object(Hash.new(0)) do |k, sorted|
      sorted[k] = self.db[k].sort
    end
  end
end
