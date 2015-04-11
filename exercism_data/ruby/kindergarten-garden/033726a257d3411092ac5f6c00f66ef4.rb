class Garden
  PLANTS = { 'C' => :clover, 'G' => :grass, 'R' => :radishes, 'V' => :violets }
  STUDENTS = %w( Alice Bob Charlie David Eve Fred Ginny Harriet
                 Ileana Joseph Kincaid Larry )

  attr_reader :plots

  def initialize(diagram, students = STUDENTS)
    @plots = students.sort
             .map { |s| s.downcase.to_sym }
             .zip(plots_from diagram).to_h
  end

  def method_missing(student)
    super unless plots.keys.include?(student)
    plots[student]
  end

  def respond_to_missing?(method_name, include_private = false)
    plots.keys.include?(student) || super
  end

  private

  def plots_from(diagram)
    first, second = diagram.each_line.map { |line| line.scan(/(.)(.)/) }
    first.zip(second).map do |ab, cd|
      [ab, cd].flatten.map { |c| PLANTS.fetch(c) }
    end
  end
end
