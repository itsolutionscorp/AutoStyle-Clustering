class Robot
  @@used_names = {}

  def name
    @name = pick_name if @name.nil?
    @name
  end

  def reset
    @name = nil
  end

  def pick_name
    str = nil
    while str.nil? or @@used_names[str]
      str = ('AA'..'ZZ').to_a.sample + ('000'..'999').to_a.sample
    end
    @@used_names[str] = true
    str
  end
end
