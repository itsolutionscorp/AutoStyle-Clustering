class School
  include Enumerable

  attr_accessor :db

  def initialize
    self.db = {}
  end

  def add(student, grade)
    self.db[grade] ||= []
    self.db[grade] << student
  end

  def grade(grade)
    self.db[grade] || []
  end

  def each(&block)
    self.db.each do |k, v|
      block.call(k,v)
    end
  end

  def sort
    {}.tap do |r|
      super.each do |val|
        r[val.first] = val.last.sort
      end
    end
  end
end
