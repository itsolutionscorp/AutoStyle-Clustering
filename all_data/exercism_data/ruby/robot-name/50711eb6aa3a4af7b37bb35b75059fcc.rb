class Robot

  @@first  = 'A'.ord
  @@second = 'A'.ord
  @@number = 0

  def name
    @name ||= generate_name
  end

  def generate_name
    "#{@@first.chr}#{@@second.chr}#{@@number.to_s.rjust(3, '0')}".tap do |new_name|
      increase_counter!
    end
  end

  def reset
    @name = generate_name
  end

  private

  def increase_counter!
    if @@number == 999
      if @@second.chr == 'Z'
        @@first  += 1
        @@second == 'A'.ord
      else
        @@second += 1
      end
    else
      @@number += 1
    end
  end
end
