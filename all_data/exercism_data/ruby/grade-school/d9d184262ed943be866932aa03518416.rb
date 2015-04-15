class School
  attr_reader :db

  def initialize
    @db = {}
  end

  def add name, number
    grade(number) << name
  end

  def grade number
    db[number] ||= []
  end

  def sort
    Hash[sorted_array]
  end

  private

  def sorted_array
    db.sort_by(&:first).map do |number, names|
      [number, names.sort]
    end
  end
end
