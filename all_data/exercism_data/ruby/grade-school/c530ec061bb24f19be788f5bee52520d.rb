class School
  attr_reader :db

  def initialize
    @db = Database.new
  end

  def add(name, grade)
    @db.create_empty(grade) unless @db[grade]
    @db[grade] << name
  end

  def grade(number)
    @db[number] || []
  end

  def sort
    sorted_grades = {}
    @db.each { |grade,students| sorted_grades[grade] = students.sort }
    Hash[sorted_grades.sort]
  end

  private
  def db=(value)
    @db = value
  end

  class Database < Hash
    protected :[]=

    def create_empty(grade)
      self[grade] ||= []
    end
  end

end

# Additional tests to verify safety of db
class SchoolTest < MiniTest::Test
  def test_modify_db
    school.add("Ted", 4)
    original = { 4 => ["Ted"] }
    assert_raises(NoMethodError) { school.db = {4 => ["Evil Ted"]} }
    assert_raises(NoMethodError) { school.db[4] = "Super Evil Ted" }
    assert_equal original, school.db
  end
end
