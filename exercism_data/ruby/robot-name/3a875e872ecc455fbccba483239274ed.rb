class Robot
  @@names ||= []

  def name
    return @memo_name unless @memo_name.nil?
    generated_name = generate_name
    unless @@names.include?(generated_name)
      @memo_name ||= generated_name
      @@names << @memo_name
    else
      name
    end
    @memo_name
  end

  def reset
    @memo_name = nil
  end

  private

  def generate_name
    ('A'..'Z').to_a.shuffle[0, 2].join << rand(9999).to_s.rjust(4, '0')
  end
end
