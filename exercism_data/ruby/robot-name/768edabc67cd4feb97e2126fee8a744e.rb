# Robot Class
class Robot
  def initialize
    @name = ''
  end

  def name
    generate_name if no_name?
    @name
  end

  def reset
    @name = ''
  end

  private

  def generate_name
    2.times { @name << all_letters.chars[rand(all_letters.size)] }
    3.times { @name << all_digits.chars[rand(all_digits.size)] }
  end

  def all_letters
    'ABCDEFGHJKLMNPQRSTUVWXYZ'
  end

  def all_digits
    '123456789'
  end

  def no_name?
    @name == ''
  end
end
