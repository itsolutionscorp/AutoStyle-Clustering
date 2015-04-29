class School
  attr_reader :db

  def initialize
    self.db = Hash.new {|h,k| h[k] = [] }
  end

  def add(name, grade_number)
    db[grade_number] << name
  end

  def grade(grade_number)
    db[grade_number]
  end

  def sort
    db.sort.each.with_object({}) do |record, sorted_db|
      grade_number, names = record
      sorted_db[grade_number] = names.sort
    end
  end

  private

  attr_writer :db
end
