class Robot

  def name
    @name ||= 'aa' + rand(999).to_s.rjust(3, '0')
  end

  def reset
    @name = nil
  end

end
