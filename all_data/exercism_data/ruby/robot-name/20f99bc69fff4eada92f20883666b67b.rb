class Robot
  @@name_list = []

  def name
    @name ||= generate_unique_name
  end

  def reset
    @name = false
  end

  private

  def generate_name
    temp = 2.times.reduce('') do |acc, _|
      acc + ('A'..'Z').entries.shuffle[rand(26)]
    end
    3.times.reduce(temp) do |acc, _|
      acc + (0..9).entries.shuffle[rand(10)].to_s
    end
  end

  def generate_unique_name
    temp = ''
    while temp.empty?
      temp = generate_name
      if @@name_list.include?(temp)
        temp = ''
      else
        @@name_list.push(temp)
      end
    end
    temp
  end
end

# Additional test added:
def test_names_unique
  names = []
  1000.times do
    names.push(Robot.new.name)
  end
  assert names.uniq == names
end
