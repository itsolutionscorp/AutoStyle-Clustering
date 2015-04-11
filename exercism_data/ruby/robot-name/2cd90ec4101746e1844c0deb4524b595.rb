class Robot
  attr_reader :name

  @@used_names = []

  def initialize
    reset
  end

  def reset
    begin
      @name = ""

      2.times { @name << ('A'..'Z').to_a.sample }
      3.times { @name << ('0'..'9').to_a.sample }

    end while @@used_names.include?(@name)

    @@used_names << @name
  end
end
