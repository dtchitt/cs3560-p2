package src.utils;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
/**
 * Timestamp is a general purpose that that can be used freely or with composition to give instances timestamps
 * Creation time is final and cannot be modified
 */
public class TimeStamp {
	private final long createStamp;
	private long updateStamp;

	public TimeStamp() {
		this.createStamp = System.currentTimeMillis();
		this.updateStamp = createStamp;
	}

	public void setUpdateStamp() {
		this.updateStamp = System.currentTimeMillis();
	}

	public long getUpdateStamp() {
		return updateStamp;
	}

	public long getCreationStamp() {
		return createStamp;
	}

	public String getCreationDate() {
		Date date = new Date(this.createStamp);
		return this.toLocalDateTime(date);
	}

	public String getUpdateDate() {
		Date date = new Date(this.updateStamp);
		return this.toLocalDateTime(date);
	}

	/**
	 * @param date
	 * @return the local time for the user view
	 */
	private String toLocalDateTime(Date date) {
		LocalDateTime local = Instant.ofEpochMilli(date.getTime())
				.atZone(ZoneId.systemDefault())
				.toLocalDateTime();

		return local.format(DateTimeFormatter.ofPattern("MMM d uuuu HH:mm:ss"));
	}
}
