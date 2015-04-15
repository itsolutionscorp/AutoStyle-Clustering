class Garden
  
  STUDENTS = %w(Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry)
  PLANT_MAP = {V: :violets, R: :radishes, G: :grass, C: :clover}
  
  def initialize(plants,students=nil)
    @plants = plants
    @students = (students || STUDENTS).sort
    @students.each do |kid|
      self.class.send(:define_method,"#{kid.downcase}") do
        codes = []
        rows = @plants.split("\n")
        cols = @students.index kid
        row_0_0 = rows[0][cols*2]
        row_0_1 = rows[0][cols*2+1]
        row_1_0 = rows[1][cols*2]
        row_1_1 = rows[1][cols*2+1]
        codes = [row_0_0, row_0_1,row_1_0, row_1_1]
        codes.map {|c| PLANT_MAP[c.to_sym]}
      end
    end
  end

end
