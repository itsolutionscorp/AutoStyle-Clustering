class School
  def initialize
    @roster = {};
  end

  def add(student, grade)
    @roster[grade] ||= []
    @roster[grade] << student
    sort_roster!(grade)
  end

  def to_hash
    roster_sorted_by_grade
  end

  def grade(grade)
    @roster[grade] || []
  end

  private

  def roster_sorted_by_grade
    ordered_roster = {}
    @roster.keys.sort.each do |key|
      ordered_roster[key] = @roster[key]
    end
    ordered_roster
  end

  def sort_roster!(grade)
    @roster[grade].sort!
  end
end
