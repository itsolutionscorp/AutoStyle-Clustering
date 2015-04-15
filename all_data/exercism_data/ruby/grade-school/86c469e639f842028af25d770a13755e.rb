class School

  attr_reader :db

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
    self.db.keys.sort.each.with_object(Hash.new(0)) { |k, sorted| sorted[k] = self.db[k].sort }
  end

  private

  attr_writer :db
end
