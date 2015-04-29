class Robot
  attr_reader :name

  @@used_names = []

  def initialize
    begin
      name = ""

      2.times { name << ('A'..'Z').to_a[Kernel.rand(26)] }
      3.times { name << Kernel.rand(10).to_s             }

      @name = name
    end while @@used_names.include?(@name)

    @@used_names << @name
  end

  def reset
    initialize
  end
end
