class Gigasecond
    def self.from(utc_time_object)
        epoch_gigasecond = utc_time_object.to_i + 1000000000
        return Time.at(epoch_gigasecond).utc
    end
end
