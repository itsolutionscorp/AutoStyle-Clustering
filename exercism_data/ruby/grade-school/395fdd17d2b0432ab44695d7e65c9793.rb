class School

  attr_reader :db

  def initialize
    @db = Hash.new { |hash, key| hash[key] = [] }
  end

  def add(kid, grade)
    @db[grade] << kid
  end

  def grade(grade)
    @db[grade]
  end

  def sort
    Hash[@db.sort.each { |_, kids| kids.sort! }]
  end

end
