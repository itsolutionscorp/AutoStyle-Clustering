module Prime
  def self.nth desired_idx
    raise ArgumentError if desired_idx <= 0
    current_num = 2
    current_idx = 1
    until current_idx.eql? desired_idx
      current_num += 1
      current_idx += 1 if is_prime? current_num
    end
    current_num
  end

  def self.is_prime? candidate
    return (2..candidate / 2).find {|n| candidate % n == 0 }.nil?
  end
end
