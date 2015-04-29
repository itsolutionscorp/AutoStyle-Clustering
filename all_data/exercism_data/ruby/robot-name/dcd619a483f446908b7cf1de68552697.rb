class Robot
  @@names = []

  def initialize
    @name = nil
  end

  def name
    @name ||= generate_name
  end

  def reset
    @@names.delete(@name)
    @name = nil
  end

  private

  def generate_name
    name =  2.times.map { random_letter } * '' +
            3.times.map { random_number } * ''
    uniq?(name) ? name : generate_name
  end

  def random_letter
    ('a'..'z').to_a[rand(26)]
  end

  def random_number
    rand(9)
  end

  def uniq?(name)
    not @@names.include? name
  end
end
