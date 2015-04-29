class Robot
  WORD_CHARACTERS=('a'..'z').to_a.concat(('A'..'Z').to_a)

  def name
    @name ||= generate_name
  end

  def reset
    @name = nil
  end

  private

  def generate_name
    value = ""

    2.times {
      i = rand(WORD_CHARACTERS.length - 1)
      value << WORD_CHARACTERS[i]
   }

    3.times {
      value << rand(9).to_s
    }

    return value
  end
end
