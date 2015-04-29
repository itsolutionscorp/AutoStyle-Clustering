class Robot
  NUMS = ('000'..'999').to_a
  CHARS = ('AA'..'ZZ').to_a
  @@prev_names = [nil]

  def name
    unless @name
      while @@prev_names.include? @name
        @name = generate_name
      end
      @@prev_names << @name
    end
    @name
  end

  def reset
    @name = nil
  end

  def generate_name
    "#{CHARS.sample}#{NUMS.sample}"
  end
end
