class School
  def initialize(name)
    @name = name
    @db = {}
  end

  def add(student, grade_level)
    ensure_grade(grade_level) << student
  end

  def grade(level)
    @db.fetch(level, [])
  end

  def db
    deep_copy_hash(@db)
  end

  def sort
    sorted = db.sort.map { |grade_level, students| [grade_level, students.sort] }
    Hash[sorted]
  end

  private

  def ensure_grade(level)
    @db[level] ||= []
  end

  def deep_copy_hash(hash)
    hash_without_default_proc = Hash[hash]
    Marshal.load(Marshal.dump(hash_without_default_proc))
  end
end


# Additional tests. Kudos to Sandi Metz for the idea.

require "minitest/autorun"
class AdditionalSchoolTests < MiniTest::Unit::TestCase
  def school
    @school
  end

  def setup
    @school = School.new("Haleakala Hippy School")
  end

  # The README claims this behavior is needed, but it wasn't speced.
  def test_sort_school_also_sorts_keys
    school.add("Jennifer", 4)
    school.add("Kareem", 6)
    school.add("Kyle", 3)
    assert_equal [3, 4, 6], school.sort.keys
  end

  # I'd rather just not expose the DB at all, but if we do, I want this.
  def test_db_is_not_directly_mutable_from_the_outside
    school.add("Jennifer", 4)
    school.db.delete(4)
    school.db[4] << "Voldemort"
    assert_equal({ 4 => ["Jennifer"] }, school.db)
  end

  def test_sort_is_not_directly_mutable_from_the_outside
    school.add("Jennifer", 4)
    school.sort.delete(4)
    school.sort[4] << "Voldemort"
    assert_equal({ 4 => ["Jennifer"] }, school.sort)
  end

  # This seems sensible.
  def test_querying_doesnt_modify_database
    school.add("Jennifer", 4)
    school.grade(123)
    assert_equal({ 4 => ["Jennifer"] }, school.db)
  end
end
