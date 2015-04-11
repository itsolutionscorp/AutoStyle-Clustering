class Robot
  @@names = []
  attr_reader :name
  def initialize
    begin
      @name = ''
      2.times { @name << ('A'..'Z').to_a.sample }
      3.times { @name << (1..9).to_a.sample.to_s }
    end while @@names.include?(@name)
    @@names << @name
  end

  def reset
    initialize
  end
end
