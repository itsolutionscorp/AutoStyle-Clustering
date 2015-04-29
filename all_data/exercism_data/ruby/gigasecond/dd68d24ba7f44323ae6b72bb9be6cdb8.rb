require 'time'
class Gigasecond
  def self.from(dob)
    onegig_dob = dob.to_i + 1000000000
    Time.at onegig_dob
  end
end
