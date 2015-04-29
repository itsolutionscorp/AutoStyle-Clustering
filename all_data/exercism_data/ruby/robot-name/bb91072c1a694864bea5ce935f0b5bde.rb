class Robot

  def name
    @name ||= random_letter + random_letter + random_number
    name if @old_names.include?(@name)
    @name
  end

  def initialize
    @old_names = []
  end

  def reset
    @old_names << name
    @name = nil
  end

  def random_letter
    rand("A".ord.."Z".ord).chr
  end

  def random_number
    rand(0..999).to_s.rjust(3,"0")
  end

end
