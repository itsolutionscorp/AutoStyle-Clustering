class Robot

  def initialize
    @name = ""
  end

  def name

    if @name.empty?
      let_arr = []
      num_arr = []

      ("A".."Z").each do |let|
        let_arr << let
      end

      (0..9).each do |num|
        num_arr << num
      end

      2.times do
        @name += let_arr.sample
      end

      3.times do
        @name += num_arr.sample.to_s
      end
    end

    return @name

  end

  def reset
    @name = ""
  end

end
