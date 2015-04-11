class Garden
  def initialize diagram, students = nil
    @diagram = diagram
    @students =
      if students
        students.map(&:downcase).map(&:to_sym).sort
      else
        %i( alice bob charlie david eve fred ginny harriet ileana joseph kincaid larry )
      end
  end

  PLANTS = {
    'R' => :radishes,
    'C' => :clover,
    'G' => :grass,
    'V' => :violets,
  }

  def method_missing(name, *args, &block)
    if students.include? name
      chunk name
    else
      super
    end
  end

  private

  attr_reader :diagram, :students

  def chunk student
    offset = students.index(student) * 2
    diagram
      .lines
      .map { |line| line[offset..offset+1].chars }
      .flatten
      .map { |c| PLANTS[c] }
  end
end
